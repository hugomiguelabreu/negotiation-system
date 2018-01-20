-module(orders).
-export([init/0]).

-include("order.hrl").
-include("getJson.hrl").

%input port set to 3000
%export port set to 3001


init() ->
	io:format("start orders\n"),
	spawn(fun() -> start_from_client() end),
	spawn(fun() -> start_from_exchange() end).

%%====================================================================
%% from client
%%====================================================================

% esta a escuta na porta 3000 de clientes que queiram comprar
start_from_client() -> 
	{ok, LSocket} =  gen_tcp:listen(3000, [binary, {reuseaddr, true}, {packet, 1}]),
	spawn(fun() -> acceptor_from_client(LSocket) end),
	receive
		kek -> 
			io:format("kek\n")
	end.

acceptor_from_client(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	spawn(fun() -> acceptor_from_client(LSock) end),
	forwarder_from_client(Sock).

forwarder_from_client(Socket) -> 
	receive
		{tcp, Socket, Data} ->
			Ret = order:decode_msg(Data, 'Order'),
			Symbol = Ret#'Order'.symbol,
			Username = Ret#'Order'.user,
			case db:select_cache(Symbol) of
				{ok, Addr} -> 
					List = binary:bin_to_list(Addr),
					send_to_exchange(Data, List, Username);
				undefined ->
					Naddr = getJson:getCompany(Symbol),
					ListU = binary:bin_to_list(Naddr),
					send_to_exchange(Data, ListU, Username)
			end,
			forwarder_from_client(Socket);
		{tcp_closed, _} ->
      		io:format("user closed\n");
    	_ ->
      		io:format("error\n")
	end.

send_to_exchange(Data, List, Username) ->
	Tokens = string:tokens(List, ":"),
	{Port, _ } = string:to_integer(lists:last(Tokens)),
	{ok, Sock} = gen_tcp:connect("localhost", Port, [binary, {reuseaddr, true}]),
	gen_tcp:send(Sock, Data),
	db:insert_positions(Username, Sock).


%%====================================================================
%% from exchange
%%====================================================================

% esta a escuta na porta 3001 de cenas que venham da exchange
start_from_exchange() -> 
	{ok, LSocket} =  gen_tcp:listen(3001, [binary, {reuseaddr, true}, {packet, 1}]),
	spawn(fun() -> acceptor_from_exchange(LSocket) end),
	receive
		kek -> 
			io:format("kek\n")
	end.

acceptor_from_exchange(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	spawn(fun() -> acceptor_from_exchange(LSock) end),
	forwarder_from_exchange(Sock).

forwarder_from_exchange(Socket) -> 
	receive
		{tcp, Socket, Data} ->
			Ret = order:decode_msg(Data, 'Order'),
			Username = Ret#'Order'.user,
			case db:select_positions(Username) of
				{ok, Sock} ->
					gen_tcp:send(Sock, Data);
				undefined ->
					io:format("error to client\n")
			end;
		{tcp_closed, _} ->
      		io:format("exchange closed\n");
    	_ ->
      		io:format("error\n")
	end.