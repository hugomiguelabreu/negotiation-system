-module(orders).
-export([init/0]).

-include("order.hrl").

%input port set to 3000
%export port set to 3001

startOrders() ->
	login_manager:start(), %da start ao manager
	{ok, LSock} = gen_tcp:listen(3000, [binary, {nodelay, true}, {reuseaddr, true}]),
	acceptor(LSock).

acceptor(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	spawn(fun() -> acceptor(LSock) end),
	forwarder(Sock).


% este mano recebe order.proto do cliente e manda para o servidor
% forwarder(Sock) ->
% 	receive
% 		{tcp, From, Data} ->
% 			% abre o protobuff e ve o symbol
% 			% ve na cache e manda para o servidor coorespondente




init() -> 
	{ok, LSocket} =  gen_tcp:listen(3000, [binary, {nodelay, true} , {reuseaddr, true}]),
	spawn(fun() -> forwarder(LSocket) end).

forwarder(LSocket) ->
	{ok, ClientSocket} = gen_tcp:accept(LSocket),	
	spawn(fun() -> forwarder(LSocket) end),

	{ok, SvSocket} = gen_tcp:connect("localhost", 3001, [binary, {nodelay, true}, {reuseaddr, true}]),
	io:format("MANDA\n"),
	CLT_DATA = get_data(ClientSocket),
	gen_tcp:send(SvSocket, CLT_DATA),
	gen_tcp:close(SvSocket),
	io:format("Kappa").
	
	% SV_DATA = get_data(SvSocket),
	% gen_tcp:send(ClientSocket, SV_DATA).


	% required Type orderType = 0;
	% required string symbol = 1;
	% required int32 quantity = 2;
	% required double price = 3;
	% required string user = 4;

get_data(FROM)->
	receive
		{tcp, FROM, DATA} ->
			% Ret = order:decode_msg(DATA, 'Order'),
			% Type = Ret#'Order'.orderType,
			% Symbol = Ret#'Order'.symbol,
			% Quant = Ret#'Order'.quantity,
			% Price = Ret#'Order'.price,
			% User = Ret#'Order'.user,
			% io:format("decoded\n"),
			% XX = order:encode_msg(#'Order'{orderType = Type , symbol = Symbol, quantity = Quant, price = Price, user = User}),
			% XX;
			DATA;

		{tcp_closed, FROM} ->
      		exit("user closed");
    	{tcp_error, FROM, _} ->
      		exit("error")
	end.