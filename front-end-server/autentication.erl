-module(autentication).


-import (login_manager , [start/0, create_account/2, close_account/2, login/2, logout/1, online/0]).
% -import (account , [encode_msg/1, encode_msg/2, decode_msg/2, decode_msg/3]).
% -import (response , [encode_msg/1, encode_msg/2, decode_msg/2, decode_msg/3]).

-include("vendor/account.hrl").
-include("vendor/response.hrl").

-export([startAutentication/0]).

% registo -> true
% login -> false

startAutentication() ->
	login_manager:start(), %da start ao manager
	{ok, LSock} = gen_tcp:listen(2000, [binary, {nodelay, true}, {reuseaddr, true}]),
	acceptor(LSock).

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
					case (login_manager:create_account(Username, Password)) of
	        			ok -> 
			        		io:format("registo efetuado com sucesso\n"),
			        		Msg = response:encode_msg(#'Response'{rep=true}),
			        		gen_tcp:send(Sock, Msg),
			        		gen_tcp:close(Sock), %javardice, para forcar o flush
			        		gen_tcp:accept(Sock), %javardice, para forcar o flush
			        		aut(Sock);
			        	user_exists ->
			        		io:format("registo falhou\n"),
			        		Msg = response:encode_msg(#'Response'{rep=false}),
			        		gen_tcp:send(Sock, Msg),
			        		gen_tcp:close(Sock), %javardice, para forcar o flush
			        		gen_tcp:accept(Sock), %javardice, para forcar o flush
			        		aut(Sock)
	        		end;
	        	false ->
	        		case (login_manager:login(Username, Password)) of
			        	ok -> 
			        		io:format("login efetuado com sucesso\n"),
			        		Msg = response:encode_msg(#'Response'{rep=true}),
			        		gen_tcp:send(Sock, Msg),
			        		gen:tcp_close(Sock); % fechar o socket pq ja nao e preciso mas continua a dar erro pq erlang??
			        	invalid ->
			        		io:format("login falhou\n"),
			        		Msg = response:encode_msg(#'Response'{rep=false}),
			        		gen_tcp:send(Sock, Msg),
			        		gen_tcp:close(Sock), %javardice, para forcar o flush
			        		gen_tcp:accept(Sock), %javardice, para forcar o flush
			        		aut(Sock)
			        end
			end;
	    {tcp_closed, _} -> 
      		io:format("closed\n");
    	{tcp_error, _, _} -> 
      		io:format("error\n")
	end.

% login() ->
% 	{ok, LSock} = gen_tcp:listen(2001, [binary, {reuseaddr, true}]),
% 	acceptorL(LSock).

% acceptorL(LSock) ->
% 	{ok, Sock} = gen_tcp:accept(LSock),
% 	io:format("LOGIN\n")
% 	spawn(fun() -> acceptorL(LSock) end),
% 	log(Sock).

% log(Sock) ->
% 	io:format("login\n"),
% 	receive 
% 		{tcp, Sock, Data} ->
% 			Ret = account:decode_msg(Data, 'Account'),
% 			Username = Ret#'Account'.username,
% 			Password = Ret#'Account'.password,
% 			case (login_manager:login(Username, Password)) of
% 	        	ok -> 
% 	        		io:format("login efetuado com sucesso\n"),
% 	        		log(Sock);
% 	        	invalid ->
% 	        		io:format("login falhou\n"),
% 	        		log(Sock)
% 	        end;
% 	    {tcp_closed, _} -> 
%       		io:format("closed");
%     	{tcp_error, _, _} -> 
%       		io:format("error")
% 	end.

% login() ->
% 	io:format("login").






% acceptor(LSock) ->
% 	{ok, Sock} = gen_tcp:accept(LSock),
% 	% spawn(fun() -> acceptor(LSock) end),
% 	io:format("kek\n"),
% 	manda(Sock).