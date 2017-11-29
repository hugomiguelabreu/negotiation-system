-module(frontEnd).
-export([server/1]).
-import (login_manager , [start/0, create_account/2, close_account/2, login/2, logout/1, online/0]).

server(Port) ->
	login_manager:start(), %da start ao loginManager
	{ok, LSock} = gen_tcp:listen(Port, [binary, {packet, line}, {reuseaddr, true}]),
	acceptor(LSock).

acceptor(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	spawn(fun() -> acceptor(LSock) end),
	Nome = autenticacao(Sock). % Nome = nome do utilizador

autenticacao(Sock) ->
	Msg = "[l]login\n[r]registo\n",
  	gen_tcp:send(Sock, Msg),
  	receive
    	{tcp, _, Data} ->      
      		case binary:match(Data, <<"l">>) of 
        		{0,1} -> 
            		MsgU = "username:",
            		gen_tcp:send(Sock, MsgU),
            		receive
              			{tcp, _, DataUX} ->
			                Aux = byte_size(DataUX) - 2, %para tirar o \n\r no fim (\r no windows???)
			                DataU = binary:part(DataUX, {0,Aux}),
			                MsgP = "password:",
			                gen_tcp:send(Sock, MsgP),
                			receive
                  				{tcp, _, DataP} ->
                    				case (login_manager:login(DataU, DataP)) of
                      					ok -> 
                        					gen_tcp:send(Sock, "--------\nlogin efetuado com sucesso\n--------\n"),
                        					DataU;
					                    invalid -> 
					                    	gen_tcp:send(Sock, "--------\nlogin invalido\n--------\n"),
					                    	autenticacao(Sock)
                    				end
                			end
            		end;
        		nomatch -> 
          			case binary:match(Data, <<"r">>) of
            			{0,1} -> 
              				MsgRU = "username:",
              				gen_tcp:send(Sock, MsgRU),
              				receive
	                			{tcp, _, DataRUX} ->
	                  				Aux = byte_size(DataRUX) - 2, %para tirar o \n\r no fim (\r no windows???)
	                  				DataRU = binary:part(DataRUX, {0,Aux}),
	                  				MsgRP = "password:",
	                  				gen_tcp:send(Sock, MsgRP),
	                  				receive
	                    				{tcp, _, DataRP} ->
	                      					case (login_manager:create_account(DataRU, DataRP)) of
	                        					ok -> 
	                          						gen_tcp:send(Sock, "--------\nconta criada\n--------\n"),
	                          						io:format("created acount ~n"),
	                          						autenticacao(Sock);
	                        					user_exists -> 
	                          						gen_tcp:send(Sock, "--------\nnome ja existe\n--------\n"),
	                          						autenticacao(Sock)
	                      					end
	                  				end
              				end;
            			nomatch -> autenticacao(Sock)
          			end
      		end
  	end.
