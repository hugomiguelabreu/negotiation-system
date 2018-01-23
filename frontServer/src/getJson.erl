%%%-------------------------------------------------------------------
%% @doc getJson public API
%% @end
%%%-------------------------------------------------------------------

-module(getJson).

-include("db.hrl").

%% Application callbacks
-export([start/0, getCompany/1]).

%%====================================================================
%% API
%%====================================================================

start() ->
	io:format("start getJson\n"),
	inets:start().

getCompany(Name) ->
	Get = string:concat("http://localhost:8080/company/", Name),
	case httpc:request(get, {Get, []}, [], []) of
		{ok, {{_, 200, _}, _, Body}} ->	io:format("Geted\n"),
										{Raw} = jiffy:decode(Body),
										getExchange(Raw);
		_ -> io:format("error")
	end.
	

%%====================================================================
%% Internal functions
%%====================================================================

getExchange(Raw) ->
	Address = ej:get({"exchange", "addr"}, Raw),
	Id = ej:get({"id"}, Raw),
	db:insert_cache(Id, Address), % as cenas sao inseridas <<"Id">> e sao retornadas como <<"Address">>
	io:format(Address),
	io:format("\n"),
	Address.