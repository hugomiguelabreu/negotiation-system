-module(db).

% o primeiro atributo do record e a Key
-record(users, {username, password, logedIn}).
-record(cache, {id, address}).

-export ([init/0, start/0, stop/0, login/2, logout/2, register/2, insert_cache/2, select_cache/1]).

%%====================================================================
%% start mnesia
%%====================================================================

start() ->
	mnesia:wait_for_tables([users, cache], 5000),
	io:format("db started\n").

init() ->
	mnesia:create_schema([node()]),
	mnesia:start(),
	mnesia:create_table(cache, [{attributes, record_info(fields, cache)}, {ram_copies, [node()]}]),
	mnesia:create_table(users, [{attributes, record_info(fields, users)}, {disc_copies, [node()]}]),
	start().

stop() ->
	mnesia:stop().


%%====================================================================
%% users table
%%====================================================================

login(Username, Password) ->
	F = fun() ->
		case mnesia:wread({users, Username}) of
			[#users{password = Password, logedIn = false}] ->
				mnesia:write(#users{username = Username,
						   			password = Password,
						   			logedIn = true});
			_ -> io:format("pass ou men errados\n")
		end
	end,
	mnesia:activity(transaction, F).

logout(Username, Password) ->
	F = fun() ->
		case mnesia:wread({users, Username}) of
			[#users{password = Password, logedIn = true}] ->
				mnesia:write(#users{username = Username,
						   			password = Password,
						   			logedIn = false});
			_ -> io:format("error\n")
		end
	end,
	mnesia:activity(transaction, F).

register(Username, Password) ->
	F = fun() ->
		case mnesia:wread({users, Username}) of
			[_] -> 
				io:format("user ja existe\n");
			_ -> 
				mnesia:write(#users{username = Username,
						   			password = Password,
						   			logedIn = false})
		end
	end,
	mnesia:activity(transaction, F).


%%====================================================================
%% cache table
%%====================================================================

insert_cache(Id, Address) ->
	F = fun() ->
		mnesia:write(#cache{id = Id,
							address = Address})
	end,
	mnesia:activity(transaction, F),
	io:format("inserted cenas na cache\n").

select_cache(Id) ->
	F = fun() ->
		case mnesia:read({cache, Id}) of
			[#cache{address = Add}] ->
				io:format("cache HIT\n"),
				{ok, Add};
			[] ->
				io:format("cache Miss\n"),
				undefined
		end
	end,
	mnesia:activity(transaction, F).


%%====================================================================
%% lixo
%%====================================================================


% find_user(Username) ->
% 	F = fun() ->
% 		case mnesia:read({users,Username}) of
% 			[#users{password = P, logedIn = L}] ->
% 				io:format("kappa kappa\n");
% 			[] ->
% 				io:format("not Kappa\n")
% 		end
% 	end,
% 	mnesia:activity(transaction, F).

% add_user(Username, Password) ->
% 	F = fun() ->
% 		mnesia:write(#users{username = Username,
% 						   password = Password,
% 						   logedIn = false})
% 	end,
% 	mnesia:activity(transaction, F).

% find_in_cache(Id) ->
% 	F = fun() ->
% 		case mnesia:read({cache, Id}) of
% 			[#cache{address = Add}] ->
% 				io:format("cache HIT\n");
% 			[] ->
% 				io:format("cache Miss\n")
% 		end
% 	end,
% 	mnesia:activity(transaction, F).

% add_cache(Id, Address) ->
% 	F = fun() ->
% 		mnesia:write(#cache{id = Id,
% 							address = Address})
% 	end,
% 	mnesia:activity(transaction, F).