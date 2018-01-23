-module(db).

-include_lib("stdlib/include/qlc.hrl"). 

% o primeiro atributo do record e a Key
-record(users, {username, password}).
-record(cache, {id, address}).
-record(positions, {username, socket}).
-record(mailbox, {username, msg}).

-export ([init/0, start/0, stop/0, login/2, register/2, insert_cache/2, select_cache/1, insert_positions/2, select_positions/1, insert_mailbox/2, select_mailbox/1, select_all/0]).

%%====================================================================
%% start mnesia
%%====================================================================

start() ->
	mnesia:wait_for_tables([users, cache, positions, mailbox], 5000),
	io:format("db started\n").

init() ->
	mnesia:create_schema([node()]),
	mnesia:start(),
	mnesia:create_table(users, [{attributes, record_info(fields, users)}, {disc_copies, [node()]}]),
	mnesia:create_table(cache, [{attributes, record_info(fields, cache)}, {ram_copies, [node()]}]),
	mnesia:create_table(positions, [{attributes, record_info(fields, positions)}, {ram_copies, [node()]}]),
	mnesia:create_table(mailbox, [{attributes, record_info(fields, mailbox)}, {ram_copies, [node()]}]),
	start().

stop() ->
	mnesia:stop().


%%====================================================================
%% users table
%%====================================================================

login(Username, Password) ->
	F = fun() ->
		case mnesia:wread({users, Username}) of
			[#users{password = Password}] ->
				mnesia:write(#users{username = Username,
						   			password = Password}),
				ok;
			_ -> 
				io:format("pass ou men errados\n"),
				undefined
		end
	end,
	mnesia:activity(transaction, F).

% logout(Username, Password) ->
% 	F = fun() ->
% 		case mnesia:wread({users, Username}) of
% 			[#users{password = Password, logedIn = true}] ->
% 				mnesia:write(#users{username = Username,
% 						   			password = Password,
% 						   			logedIn = false});
% 			_ -> io:format("error\n")
% 		end
% 	end,
% 	mnesia:activity(transaction, F).

register(Username, Password) ->
	F = fun() ->
		case mnesia:wread({users, Username}) of
			[_] -> 
				io:format("user ja existe\n"),
				undefined;
			_ -> 
				mnesia:write(#users{username = Username,
						   			password = Password}),
				ok
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
	io:format("endereco inserido na cache\n").

select_cache(Id) ->
	F = fun() ->
		case mnesia:read({cache, Id}) of
			[#cache{address = Add}] ->
				io:format("cache HIT\n"),
				{ok, Add};
			[] ->
				io:format("cache MISS\n"),
				undefined
		end
	end,
	mnesia:activity(transaction, F).

%%====================================================================
%% positions table
%%====================================================================

insert_positions(Username, Socket) ->
	F = fun() ->
		mnesia:write(#positions{username = Username,
								socket = Socket})
	end,
	mnesia:activity(transaction, F).

select_positions(Username) ->
	F = fun() ->
		case mnesia:read({positions, Username}) of
			[#positions{socket = Socket}] ->
				{ok, Socket};
			[] ->
				undefined
		end
	end,
	mnesia:activity(transaction, F).

%%====================================================================
%% mailbox table
%%====================================================================

insert_mailbox(Username, Msg) ->
	io:format("insert mailbox\n"),
	F = fun() ->
		case mnesia:wread({mailbox, Username}) of
			[#mailbox{msg = []}] ->
				mnesia:write(#mailbox{username = Username,
									  msg = [Msg]}),
				ok;
			[#mailbox{msg = List}] ->
				mnesia:write(#mailbox{username = Username,
									  msg = lists:append(List, [Msg])}),
				ok;
			_ ->
				mnesia:write(#mailbox{username = Username,
									  msg = []}),
				ok
		end
	end,
	mnesia:activity(transaction, F).


select_mailbox(Username) ->
	io:format("checking mailbox\n"),
	F = fun() ->
		case mnesia:read({mailbox, Username}) of
			[#mailbox{msg = []}] ->
				noMsgs;
			[#mailbox{msg = List}] ->
				mnesia:write(#mailbox{username = Username,
									  msg = []}), % elimina a entrada da tabela pq ja vai ser enviada
				{ok, List};
			[] ->
				undefined
		end
	end,
	mnesia:activity(transaction, F).



select_all() -> 
    mnesia:transaction( 
    fun() ->
        qlc:eval( qlc:q(
            [ X || X <- mnesia:table(mailbox)] 
        )) 
    end ).