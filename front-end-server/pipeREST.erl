-module(pipeREST).
-export([pipeREST/1]).

pipeREST(Port) ->
	{ok, LSock} = gen_tcp:listen(Port, [binary, {packet, 0}, {reuseaddr, true}, {active, true}]),
	acceptor(LSock).

acceptor(LSock) ->
	{ok, Sock} = gen_tcp:accept(LSock),
	{ok, SSock} = gen_tcp:connect("localhost", 80, [binary, {packet, 0}]),
	spawn(fun() -> acceptor(LSock) end),
	pipe(SSock, Sock).

pipe(SSock, Sock) ->
	receive
		{tcp, Sock, Data} ->
			io:format("received ~p~n", [Data]),
            gen_tcp:send(SSock, Data),
			receive
				{tcp, SSock, DataR} ->
					io:format("received ~p~n", [DataR]),
					gen_tcp:send(Sock, DataR);
				{tcp_closed, Sock} ->	
					io:format("user disconnected~n", []);
				{tcp_error, Sock, _} ->
					io:format("tcp error~n", [])
			end;
		{tcp_closed, Sock} ->
			io:format("user disconnected~n", []);
		{tcp_error, Sock, _} ->
			io:format("tcp error~n", [])
	end.