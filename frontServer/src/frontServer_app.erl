%%%-------------------------------------------------------------------
%% @doc frontServer public API
%% @end
%%%-------------------------------------------------------------------

-module(frontServer_app).

%% Imports
-include ("db.hrl").
-include ("autentication.hrl").
-include ("getJson.hrl").
-include ("orders.hrl").

%% Application callbacks
-export([start/0, stop/1]).

%%====================================================================
%% API
%%====================================================================

start() ->
	spawn(db, init, []),
	spawn(autentication, startAutentication, []),
	spawn(getJson, start, []),
	spawn(orders, init, []).

%%--------------------------------------------------------------------

stop(_State) ->
    ok.
