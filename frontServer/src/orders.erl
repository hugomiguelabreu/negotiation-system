-module(orders).
-export([init/0]).

-include("order.hrl").
-include("getJson.hrl").

%input port set to 3000
%export port set to 3001


init() -> 
	io:format("start orders\n"),
	{ok, LSocket} =  gen_tcp:listen(3000, [binary, {reuseaddr, true}, {packet, 1}]),
	acceptor(LSocket).

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


forwarder(Socket) -> 
	receive
		{tcp, Socket, Data} ->
			Ret = order:decode_msg(Data, 'Order'),
			Symbol = Ret#'Order'.symbol,
			case db:select_cache(Symbol) of
				{ok, Addr} -> 
					List = binary:bin_to_list(Addr),
					Tokens = string:tokens(List, ":"),
					Port = string:to_integer(lists:last(Tokens)),
					Packed = order:encode_msg(Ret,'Order'),
					{ok, Sock} = gen_tcp:connect("localhost", Port, [binary, {reuseaddr, true}]),
					gen_tcp:send(Sock, Packed),
					gen_tcp:close(Sock);
				undefined ->
					Naddr = getJson:getCompany(Symbol),
					ListU = binary:bin_to_list(Naddr),
					TokensU = string:tokens(ListU, ":"),
					PortU = string:to_integer(lists:last(TokensU)),
					% PackedU = repack(Ret),
					io:format("kappa\n"),
					{ok, SockU} = gen_tcp:connect("localhost", 3001, [binary, {reuseaddr, true}]),
					io:format("vai mandar as cenas\n"),
					gen_tcp:send(SockU, Data),
					gen_tcp:close(SockU)
			end;
		{tcp_closed, From} ->
      		exit("user closed");
    	{tcp_error, From, _} ->
      		exit("error")
	end.


repack(Ret) ->
	Type = Ret#'Order'.orderType,
	Symbol = Ret#'Order'.symbol,
	Quant = Ret#'Order'.quantity,
	Price = Ret#'Order'.price,
	io:format("symbol = "),
	io:format(Symbol),
	io:format("\n"),
	User = Ret#'Order'.user,
	io:format("user = "),
	io:format(User),
	io:format("\n"),
	io:format("decoded\n"),
	Packed = order:encode_msg(Ret),
	Packed.



% forwarder(LSocket) ->
% 	{ok, ClientSocket} = gen_tcp:accept(LSocket),	
% 	spawn(fun() -> forwarder(LSocket) end),

% 	{ok, SvSocket} = gen_tcp:connect("localhost", 3001, [binary, {nodelay, true}, {reuseaddr, true}]),
% 	io:format("MANDA\n"),
% 	CLT_DATA = get_data(ClientSocket),
% 	gen_tcp:send(SvSocket, CLT_DATA),
% 	gen_tcp:close(SvSocket),
% 	io:format("Kappa").
	
% 	% SV_DATA = get_data(SvSocket),
% 	% gen_tcp:send(ClientSocket, SV_DATA).


% 	% required Type orderType = 0;
% 	% required string symbol = 1;
% 	% required int32 quantity = 2;
% 	% required double price = 3;
% 	% required string user = 4;

% get_data(FROM)->
% 	receive
% 		{tcp, FROM, DATA} ->
% 			% Ret = order:decode_msg(DATA, 'Order'),
% 			% Type = Ret#'Order'.orderType,
% 			% Symbol = Ret#'Order'.symbol,
% 			% Quant = Ret#'Order'.quantity,
% 			% Price = Ret#'Order'.price,
% 			% User = Ret#'Order'.user,
% 			% io:format("decoded\n"),
% 			% XX = order:encode_msg(#'Order'{orderType = Type , symbol = Symbol, quantity = Quant, price = Price, user = User}),
% 			% XX;
% 			DATA;

% 		{tcp_closed, FROM} ->
%       		exit("user closed");
%     	{tcp_error, FROM, _} ->
%       		exit("error")
% 	end.