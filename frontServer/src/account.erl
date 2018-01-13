%% -*- coding: utf-8 -*-
%% Automatically generated, do not edit
%% Generated by gpb_compile version 4.1.1
-module(account).

-export([encode_msg/1, encode_msg/2]).
-export([decode_msg/2, decode_msg/3]).
-export([merge_msgs/2, merge_msgs/3]).
-export([verify_msg/1, verify_msg/2]).
-export([get_msg_defs/0]).
-export([get_msg_names/0]).
-export([get_group_names/0]).
-export([get_msg_or_group_names/0]).
-export([get_enum_names/0]).
-export([find_msg_def/1, fetch_msg_def/1]).
-export([find_enum_def/1, fetch_enum_def/1]).
-export([enum_symbol_by_value/2, enum_value_by_symbol/2]).
-export([get_service_names/0]).
-export([get_service_def/1]).
-export([get_rpc_names/1]).
-export([find_rpc_def/2, fetch_rpc_def/2]).
-export([get_package_name/0]).
-export([gpb_version_as_string/0, gpb_version_as_list/0]).

-include("account.hrl").
-include("gpb.hrl").

%% enumerated types

-export_type([]).

%% message types
-type 'Account'() :: #'Account'{}.
-export_type(['Account'/0]).

-spec encode_msg(#'Account'{}) -> binary().
encode_msg(Msg) -> encode_msg(Msg, []).


-spec encode_msg(#'Account'{}, list()) -> binary().
encode_msg(Msg, Opts) ->
    case proplists:get_bool(verify, Opts) of
      true -> verify_msg(Msg, Opts);
      false -> ok
    end,
    TrUserData = proplists:get_value(user_data, Opts),
    case Msg of
      #'Account'{} -> e_msg_Account(Msg, TrUserData)
    end.



e_msg_Account(Msg, TrUserData) ->
    e_msg_Account(Msg, <<>>, TrUserData).


e_msg_Account(#'Account'{type = F1, username = F2,
			 password = F3},
	      Bin, TrUserData) ->
    B1 = begin
	   TrF1 = id(F1, TrUserData),
	   e_type_bool(TrF1, <<Bin/binary, 8>>)
	 end,
    B2 = begin
	   TrF2 = id(F2, TrUserData),
	   e_type_string(TrF2, <<B1/binary, 18>>)
	 end,
    begin
      TrF3 = id(F3, TrUserData),
      e_type_string(TrF3, <<B2/binary, 26>>)
    end.

e_type_bool(true, Bin) -> <<Bin/binary, 1>>;
e_type_bool(false, Bin) -> <<Bin/binary, 0>>;
e_type_bool(1, Bin) -> <<Bin/binary, 1>>;
e_type_bool(0, Bin) -> <<Bin/binary, 0>>.

e_type_string(S, Bin) ->
    Utf8 = unicode:characters_to_binary(S),
    Bin2 = e_varint(byte_size(Utf8), Bin),
    <<Bin2/binary, Utf8/binary>>.

e_varint(N, Bin) when N =< 127 -> <<Bin/binary, N>>;
e_varint(N, Bin) ->
    Bin2 = <<Bin/binary, (N band 127 bor 128)>>,
    e_varint(N bsr 7, Bin2).


decode_msg(Bin, MsgName) when is_binary(Bin) ->
    decode_msg(Bin, MsgName, []).

decode_msg(Bin, MsgName, Opts) when is_binary(Bin) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'Account' ->
	  try d_msg_Account(Bin, TrUserData) catch
	    Class:Reason ->
		StackTrace = erlang:get_stacktrace(),
		error({gpb_error,
		       {decoding_failure,
			{Bin, 'Account', {Class, Reason, StackTrace}}}})
	  end
    end.



d_msg_Account(Bin, TrUserData) ->
    dfp_read_field_def_Account(Bin, 0, 0,
			       id(undefined, TrUserData),
			       id(undefined, TrUserData),
			       id(undefined, TrUserData), TrUserData).

dfp_read_field_def_Account(<<8, Rest/binary>>, Z1, Z2,
			   F@_1, F@_2, F@_3, TrUserData) ->
    d_field_Account_type(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			 TrUserData);
dfp_read_field_def_Account(<<18, Rest/binary>>, Z1, Z2,
			   F@_1, F@_2, F@_3, TrUserData) ->
    d_field_Account_username(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			     TrUserData);
dfp_read_field_def_Account(<<26, Rest/binary>>, Z1, Z2,
			   F@_1, F@_2, F@_3, TrUserData) ->
    d_field_Account_password(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			     TrUserData);
dfp_read_field_def_Account(<<>>, 0, 0, F@_1, F@_2, F@_3,
			   _) ->
    #'Account'{type = F@_1, username = F@_2,
	       password = F@_3};
dfp_read_field_def_Account(Other, Z1, Z2, F@_1, F@_2,
			   F@_3, TrUserData) ->
    dg_read_field_def_Account(Other, Z1, Z2, F@_1, F@_2,
			      F@_3, TrUserData).

dg_read_field_def_Account(<<1:1, X:7, Rest/binary>>, N,
			  Acc, F@_1, F@_2, F@_3, TrUserData)
    when N < 32 - 7 ->
    dg_read_field_def_Account(Rest, N + 7, X bsl N + Acc,
			      F@_1, F@_2, F@_3, TrUserData);
dg_read_field_def_Account(<<0:1, X:7, Rest/binary>>, N,
			  Acc, F@_1, F@_2, F@_3, TrUserData) ->
    Key = X bsl N + Acc,
    case Key of
      8 ->
	  d_field_Account_type(Rest, 0, 0, F@_1, F@_2, F@_3,
			       TrUserData);
      18 ->
	  d_field_Account_username(Rest, 0, 0, F@_1, F@_2, F@_3,
				   TrUserData);
      26 ->
	  d_field_Account_password(Rest, 0, 0, F@_1, F@_2, F@_3,
				   TrUserData);
      _ ->
	  case Key band 7 of
	    0 ->
		skip_varint_Account(Rest, 0, 0, F@_1, F@_2, F@_3,
				    TrUserData);
	    1 ->
		skip_64_Account(Rest, 0, 0, F@_1, F@_2, F@_3,
				TrUserData);
	    2 ->
		skip_length_delimited_Account(Rest, 0, 0, F@_1, F@_2,
					      F@_3, TrUserData);
	    3 ->
		skip_group_Account(Rest, Key bsr 3, 0, F@_1, F@_2, F@_3,
				   TrUserData);
	    5 ->
		skip_32_Account(Rest, 0, 0, F@_1, F@_2, F@_3,
				TrUserData)
	  end
    end;
dg_read_field_def_Account(<<>>, 0, 0, F@_1, F@_2, F@_3,
			  _) ->
    #'Account'{type = F@_1, username = F@_2,
	       password = F@_3}.

d_field_Account_type(<<1:1, X:7, Rest/binary>>, N, Acc,
		     F@_1, F@_2, F@_3, TrUserData)
    when N < 57 ->
    d_field_Account_type(Rest, N + 7, X bsl N + Acc, F@_1,
			 F@_2, F@_3, TrUserData);
d_field_Account_type(<<0:1, X:7, Rest/binary>>, N, Acc,
		     _, F@_2, F@_3, TrUserData) ->
    {NewFValue, RestF} = {X bsl N + Acc =/= 0, Rest},
    dfp_read_field_def_Account(RestF, 0, 0, NewFValue, F@_2,
			       F@_3, TrUserData).

d_field_Account_username(<<1:1, X:7, Rest/binary>>, N,
			 Acc, F@_1, F@_2, F@_3, TrUserData)
    when N < 57 ->
    d_field_Account_username(Rest, N + 7, X bsl N + Acc,
			     F@_1, F@_2, F@_3, TrUserData);
d_field_Account_username(<<0:1, X:7, Rest/binary>>, N,
			 Acc, F@_1, _, F@_3, TrUserData) ->
    {NewFValue, RestF} = begin
			   Len = X bsl N + Acc,
			   <<Bytes:Len/binary, Rest2/binary>> = Rest,
			   {binary:copy(Bytes), Rest2}
			 end,
    dfp_read_field_def_Account(RestF, 0, 0, F@_1, NewFValue,
			       F@_3, TrUserData).

d_field_Account_password(<<1:1, X:7, Rest/binary>>, N,
			 Acc, F@_1, F@_2, F@_3, TrUserData)
    when N < 57 ->
    d_field_Account_password(Rest, N + 7, X bsl N + Acc,
			     F@_1, F@_2, F@_3, TrUserData);
d_field_Account_password(<<0:1, X:7, Rest/binary>>, N,
			 Acc, F@_1, F@_2, _, TrUserData) ->
    {NewFValue, RestF} = begin
			   Len = X bsl N + Acc,
			   <<Bytes:Len/binary, Rest2/binary>> = Rest,
			   {binary:copy(Bytes), Rest2}
			 end,
    dfp_read_field_def_Account(RestF, 0, 0, F@_1, F@_2,
			       NewFValue, TrUserData).

skip_varint_Account(<<1:1, _:7, Rest/binary>>, Z1, Z2,
		    F@_1, F@_2, F@_3, TrUserData) ->
    skip_varint_Account(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			TrUserData);
skip_varint_Account(<<0:1, _:7, Rest/binary>>, Z1, Z2,
		    F@_1, F@_2, F@_3, TrUserData) ->
    dfp_read_field_def_Account(Rest, Z1, Z2, F@_1, F@_2,
			       F@_3, TrUserData).

skip_length_delimited_Account(<<1:1, X:7, Rest/binary>>,
			      N, Acc, F@_1, F@_2, F@_3, TrUserData)
    when N < 57 ->
    skip_length_delimited_Account(Rest, N + 7,
				  X bsl N + Acc, F@_1, F@_2, F@_3, TrUserData);
skip_length_delimited_Account(<<0:1, X:7, Rest/binary>>,
			      N, Acc, F@_1, F@_2, F@_3, TrUserData) ->
    Length = X bsl N + Acc,
    <<_:Length/binary, Rest2/binary>> = Rest,
    dfp_read_field_def_Account(Rest2, 0, 0, F@_1, F@_2,
			       F@_3, TrUserData).

skip_group_Account(Bin, FNum, Z2, F@_1, F@_2, F@_3,
		   TrUserData) ->
    {_, Rest} = read_group(Bin, FNum),
    dfp_read_field_def_Account(Rest, 0, Z2, F@_1, F@_2,
			       F@_3, TrUserData).

skip_32_Account(<<_:32, Rest/binary>>, Z1, Z2, F@_1,
		F@_2, F@_3, TrUserData) ->
    dfp_read_field_def_Account(Rest, Z1, Z2, F@_1, F@_2,
			       F@_3, TrUserData).

skip_64_Account(<<_:64, Rest/binary>>, Z1, Z2, F@_1,
		F@_2, F@_3, TrUserData) ->
    dfp_read_field_def_Account(Rest, Z1, Z2, F@_1, F@_2,
			       F@_3, TrUserData).

read_group(Bin, FieldNum) ->
    {NumBytes, EndTagLen} = read_gr_b(Bin, 0, 0, 0, 0, FieldNum),
    <<Group:NumBytes/binary, _:EndTagLen/binary, Rest/binary>> = Bin,
    {Group, Rest}.

%% Like skipping over fields, but record the total length,
%% Each field is <(FieldNum bsl 3) bor FieldType> ++ <FieldValue>
%% Record the length because varints may be non-optimally encoded.
%%
%% Groups can be nested, but assume the same FieldNum cannot be nested
%% because group field numbers are shared with the rest of the fields
%% numbers. Thus we can search just for an group-end with the same
%% field number.
%%
%% (The only time the same group field number could occur would
%% be in a nested sub message, but then it would be inside a
%% length-delimited entry, which we skip-read by length.)
read_gr_b(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen, FieldNum)
  when N < (32-7) ->
    read_gr_b(Tl, N+7, X bsl N + Acc, NumBytes, TagLen+1, FieldNum);
read_gr_b(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen,
          FieldNum) ->
    Key = X bsl N + Acc,
    TagLen1 = TagLen + 1,
    case {Key bsr 3, Key band 7} of
        {FieldNum, 4} -> % 4 = group_end
            {NumBytes, TagLen1};
        {_, 0} -> % 0 = varint
            read_gr_vi(Tl, 0, NumBytes + TagLen1, FieldNum);
        {_, 1} -> % 1 = bits64
            <<_:64, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 8, 0, FieldNum);
        {_, 2} -> % 2 = length_delimited
            read_gr_ld(Tl, 0, 0, NumBytes + TagLen1, FieldNum);
        {_, 3} -> % 3 = group_start
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 4} -> % 4 = group_end
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 5} -> % 5 = bits32
            <<_:32, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 4, 0, FieldNum)
    end.

read_gr_vi(<<1:1, _:7, Tl/binary>>, N, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_vi(Tl, N+7, NumBytes+1, FieldNum);
read_gr_vi(<<0:1, _:7, Tl/binary>>, _, NumBytes, FieldNum) ->
    read_gr_b(Tl, 0, 0, NumBytes+1, 0, FieldNum).

read_gr_ld(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_ld(Tl, N+7, X bsl N + Acc, NumBytes+1, FieldNum);
read_gr_ld(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum) ->
    Len = X bsl N + Acc,
    NumBytes1 = NumBytes + 1,
    <<_:Len/binary, Tl2/binary>> = Tl,
    read_gr_b(Tl2, 0, 0, NumBytes1 + Len, 0, FieldNum).

merge_msgs(Prev, New) -> merge_msgs(Prev, New, []).

merge_msgs(Prev, New, Opts)
    when element(1, Prev) =:= element(1, New) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case Prev of
      #'Account'{} -> merge_msg_Account(Prev, New, TrUserData)
    end.

merge_msg_Account(#'Account'{},
		  #'Account'{type = NFtype, username = NFusername,
			     password = NFpassword},
		  _) ->
    #'Account'{type = NFtype, username = NFusername,
	       password = NFpassword}.


verify_msg(Msg) -> verify_msg(Msg, []).

verify_msg(Msg, Opts) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case Msg of
      #'Account'{} ->
	  v_msg_Account(Msg, ['Account'], TrUserData);
      _ -> mk_type_error(not_a_known_message, Msg, [])
    end.


-dialyzer({nowarn_function,v_msg_Account/3}).
v_msg_Account(#'Account'{type = F1, username = F2,
			 password = F3},
	      Path, _) ->
    v_type_bool(F1, [type | Path]),
    v_type_string(F2, [username | Path]),
    v_type_string(F3, [password | Path]),
    ok.

-dialyzer({nowarn_function,v_type_bool/2}).
v_type_bool(false, _Path) -> ok;
v_type_bool(true, _Path) -> ok;
v_type_bool(0, _Path) -> ok;
v_type_bool(1, _Path) -> ok;
v_type_bool(X, Path) ->
    mk_type_error(bad_boolean_value, X, Path).

-dialyzer({nowarn_function,v_type_string/2}).
v_type_string(S, Path) when is_list(S); is_binary(S) ->
    try unicode:characters_to_binary(S) of
      B when is_binary(B) -> ok;
      {error, _, _} ->
	  mk_type_error(bad_unicode_string, S, Path)
    catch
      error:badarg ->
	  mk_type_error(bad_unicode_string, S, Path)
    end;
v_type_string(X, Path) ->
    mk_type_error(bad_unicode_string, X, Path).

-spec mk_type_error(_, _, list()) -> no_return().
mk_type_error(Error, ValueSeen, Path) ->
    Path2 = prettify_path(Path),
    erlang:error({gpb_type_error,
		  {Error, [{value, ValueSeen}, {path, Path2}]}}).


prettify_path([]) -> top_level;
prettify_path(PathR) ->
    list_to_atom(lists:append(lists:join(".",
					 lists:map(fun atom_to_list/1,
						   lists:reverse(PathR))))).


-compile({inline,id/2}).
id(X, _TrUserData) -> X.


get_msg_defs() ->
    [{{msg, 'Account'},
      [#field{name = type, fnum = 1, rnum = 2, type = bool,
	      occurrence = required, opts = []},
       #field{name = username, fnum = 2, rnum = 3,
	      type = string, occurrence = required, opts = []},
       #field{name = password, fnum = 3, rnum = 4,
	      type = string, occurrence = required, opts = []}]}].


get_msg_names() -> ['Account'].


get_group_names() -> [].


get_msg_or_group_names() -> ['Account'].


get_enum_names() -> [].


fetch_msg_def(MsgName) ->
    case find_msg_def(MsgName) of
      Fs when is_list(Fs) -> Fs;
      error -> erlang:error({no_such_msg, MsgName})
    end.


-spec fetch_enum_def(_) -> no_return().
fetch_enum_def(EnumName) ->
    erlang:error({no_such_enum, EnumName}).


find_msg_def('Account') ->
    [#field{name = type, fnum = 1, rnum = 2, type = bool,
	    occurrence = required, opts = []},
     #field{name = username, fnum = 2, rnum = 3,
	    type = string, occurrence = required, opts = []},
     #field{name = password, fnum = 3, rnum = 4,
	    type = string, occurrence = required, opts = []}];
find_msg_def(_) -> error.


find_enum_def(_) -> error.


-spec enum_symbol_by_value(_, _) -> no_return().
enum_symbol_by_value(E, V) ->
    erlang:error({no_enum_defs, E, V}).


-spec enum_value_by_symbol(_, _) -> no_return().
enum_value_by_symbol(E, V) ->
    erlang:error({no_enum_defs, E, V}).



get_service_names() -> [].


get_service_def(_) -> error.


get_rpc_names(_) -> error.


find_rpc_def(_, _) -> error.



-spec fetch_rpc_def(_, _) -> no_return().
fetch_rpc_def(ServiceName, RpcName) ->
    erlang:error({no_such_rpc, ServiceName, RpcName}).


get_package_name() -> account.



gpb_version_as_string() ->
    "4.1.1".

gpb_version_as_list() ->
    [4,1,1].
