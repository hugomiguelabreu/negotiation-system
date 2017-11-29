-module(login_manager).
-export([start/0, create_account/2, close_account/2, login/2, logout/1, online/0]).



start() -> 
	% io:format("start login Manager"),
	register(?MODULE, spawn(fun() -> loop(#{}) end)).

% spawn -> a função que o singleton vai executar no processo
% login_manager -> nome do singleton (?MODULE vai buscar automaticamente)

create_account(Username, Password) ->
	?MODULE ! {create_account, Username, Password, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

% ! -> manda uma mensagem
% self() -> o meu pid

close_account(Username, Password) ->
	?MODULE ! {close_account, Username, Password, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

login(Username, Password) ->
	?MODULE ! {login, Username, Password, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

logout(Username) ->
	?MODULE ! {logout, Username, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

online() ->
	?MODULE ! {online, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

loop(Mapa) ->
	receive
		{create_account, Username, Password, From} ->
			case maps:find(Username, Mapa) of
				error -> 
					From ! {?MODULE, ok},
					loop(maps:put(Username, {Password, false}, Mapa));
				_ -> 
					From ! {?MODULE, user_exists},
					loop(Mapa)
			end;

		{close_account, Username, Password, From} ->
			case maps:find(Username, Mapa) of
				{ok, {Password, _Loggedin}} -> 
					From ! {?MODULE, ok},
					loop(maps:remove(Username, Mapa));
				_ -> 
					From ! {?MODULE, invalid},
					loop(Mapa)
			end;

		{login, Username, Password, From} ->
			case maps:find(Username, Mapa) of
				{ok, {Password, false}} -> 
					From ! {?MODULE, ok},
					loop(maps:update(Username, {Password, true}, Mapa));
				_ -> 
					From ! {?MODULE, invalid},
					loop(Mapa)
			end;

		{logout, Username, From} ->
			case maps:find(Username, Mapa) of
				{ok, {ActualPassword, _Loggedin}} -> 
					From ! {?MODULE, ok},
					loop(maps:update(Username, {ActualPassword, false}, Mapa));
				_ -> 
					From ! {?MODULE, ok},
					loop(Mapa)
			end;

		{online, From} ->
			Res = [User || {User, {_Pass, true}} <- maps:to_list(Mapa)],
			From ! {?MODULE, Res},
			loop(Mapa)
	end.


