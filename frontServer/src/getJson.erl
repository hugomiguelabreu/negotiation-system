%%%-------------------------------------------------------------------
%% @doc getJson public API
%% @end
%%%-------------------------------------------------------------------

-module(getJson).

%% Application callbacks
-export([start/0, getComapanies/0, mapStart/0, addMap/2, getMap/1, getExchange/1, getAllExchanges/0]).

%%====================================================================
%% API
%%====================================================================

start() ->
	inets:start(),
	mapStart().

getExchange(Name) -> 
	case getMap(Name) of
		undefined -> io:format("error\n"); % get company/id depende to tipo do argumento que recebe
		IP -> IP
	end.


getComapanies() ->
	httpc:request(get, {"http://localhost:8080/companies", []}, [], []).

getAllExchanges() ->
	case httpc:request(get, {"http://localhost:8080/exchanges", []}, [], []) of
		{ok, {{_, 200, _}, _, Body}} ->	io:format("Geted\n"),
										{Raw} = jiffy:decode(Body),
										parseExchanges(Raw, 1); % os arrays comecam a 1 pq o gajo que fez isto e deficiente
		_ -> io:format("error")
	end.

%%====================================================================
%% Internal functions
%%====================================================================

parseExchanges(Raw, N) ->
	try ej:get({"exchanges", N, "name"}, Raw) of
		Name -> io:format(Name),
			   	io:format("\n"),
			   	IP = ej:get({"exchanges", N, "addr"}, Raw),
			  	case addMap(Name, IP) of
			   		ok -> io:format("Naice\n");
			   	  	_ -> io:format("not naice\n")
			    end,
		       	parseExchanges(Raw, N+1)
	catch
		_:_ -> io:format("finished parsing json\n")
	end.

%%====================================================================
%% Cache
%%====================================================================

mapStart() -> 
	io:format("start Map"),
	register(?MODULE, spawn(fun() -> loop(#{}) end)).

addMap(Key, Cena)->
	?MODULE ! {add, Key, Cena, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

getMap(Key)->
	?MODULE ! {find, Key, self()},
	receive{?MODULE, Res} ->
		Res				
	end.

loop(Map) ->
	receive
		{add, Key, Cena, From} ->
			case maps:find(Key, Map) of
				error -> 
					io:format("adicionada Cena\n"),
					From ! {?MODULE, ok},
					loop(maps:put(Key, Cena, Map));
				_ ->
					io:format("Cena ja existe\n"),
					From ! {?MODULE, exists},
					loop(Map)
			end;
		{find, Key, From} ->
			case maps:find(Key, Map) of
				error ->
					io:format("cena dessa key nao existe\n"),
					From ! {?MODULE, undefined},
					loop(Map);
				{ok, Value} ->
					io:format(Value),
					From ! {?MODULE, Value},
					loop(Map)
			end
	end.

% ---------------------------- lixo ------------------------

	% case ej:get({"exchanges", N, "name"}, Raw) of
	% 	undefined -> io:format("Kappa not defined\n");
	% 	Ret -> Ret,
	% 		   io:format("HIT\n"),
	% 	       parse(Raw, N+1)

	% end.

% getComapanies() ->
% 	case httpc:request(get, {"http://localhost:8080/companies", []}, [], []) of
% 		 {ok, {{_, 200, _}, _, Body}} ->	io:format("Geted\n"),
% 											{KK} = jiffy:decode(Body),
% 											Ret = proplists:get_all_values(<<"companies">>,KK),
% 											Kappa = lists:flatten(Ret),
% 											%repeat
% 											%zip(endereco, cena da lista)
% 											%lists:foreach(addMap, ) que adiciona ao map 
% 											%kappa

% 		_ -> io:format("error")
% 	end.

% do_thing({Props}) when is_list(Props) ->
%     do_thing_obj(Props);
% do_thing(List) when is_list(List) ->
%     do_thing_arr(Props);
% do_thing(Val)
%     do_think_val(Val).

% cria uma lista de com N Xs
% repeat(X,N) ->
%     lists:flatten(lists:duplicate(N,X)).