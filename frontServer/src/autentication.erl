-module(autentication).

-include("account.hrl").
-include("response.hrl").

-export([startAutentication/0]).

% registo -> true
% login -> false

%%====================================================================
%% API
%%====================================================================

startAutentication() ->
	io:format("start Autentication\n"),
	{ok, LSock} = gen_tcp:listen(2000, [binary, {reuseaddr, true}, {packet, 1}]),
	acceptor(LSock),
	receive
		kek ->
			io:format("Kek\n")
	end.

%%====================================================================
%% Internal functions
%%====================================================================

acceptor(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	spawn(fun() -> acceptor(LSock) end),
	aut(Sock).

% registo -> true
% login -> false

aut(Sock) ->
	receive
		{tcp, Sock, Data} ->
			Ret = account:decode_msg(Data, 'Account'),
			Username = Ret#'Account'.username,
			Password = Ret#'Account'.password,
			Type = Ret#'Account'.type,
			case Type of
				true ->
					case db:register(Username, Password) of
						ok -> 
							io:format("registo efetuado com sucesso\n"),
							Msg = response:encode_msg(#'Response'{rep=true}),
			        		gen_tcp:send(Sock, Msg),
			        		db:insert_mailbox(Username, []),
			        		aut(Sock);
			        	undefined ->
			        		io:format("Registo falhou\n"),
			        		Msg = response:encode_msg(#'Response'{rep=false}),
			        		gen_tcp:send(Sock, Msg),
			        		aut(Sock)
			        end;
			    false ->
			    	case db:login(Username, Password) of
			    		ok ->
			    			io:format("login efetuado com sucesso\n"),
			        		Msg = response:encode_msg(#'Response'{rep=true}),
			        		gen_tcp:send(Sock, Msg),
			        		send_mailbox(Sock, Username);
			        	undefined ->
			        		io:format("login falhou\n"),
			        		Msg = response:encode_msg(#'Response'{rep=false}),
			        		gen_tcp:send(Sock, Msg),
			        		aut(Sock)
			        end
			end;
		{tcp_closed, _} ->
			io:format("closed\n");
		_ ->
		 	io:format("error\n")
	end.

send_mailbox(Socket, Username) ->
	case db:select_mailbox(Username) of
		noMsgs ->
			gen_tcp:send(Socket, "lixo");
		{ok, List} ->
			Fun = fun(Elem) -> gen_tcp:send(Socket, Elem) end,
			lists:foreach(Fun, List);
		undefined ->
			gen_tcp:send(Socket, "lixo"),
			db:insert_mailbox(Username, [])
	end.