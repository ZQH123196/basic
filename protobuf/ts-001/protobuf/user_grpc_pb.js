// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('grpc');
var user_pb = require('./user_pb.js');

function serialize_userService_GetUserByIdReq(arg) {
  if (!(arg instanceof user_pb.GetUserByIdReq)) {
    throw new Error('Expected argument of type userService.GetUserByIdReq');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_userService_GetUserByIdReq(buffer_arg) {
  return user_pb.GetUserByIdReq.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_userService_GetUserByIdRes(arg) {
  if (!(arg instanceof user_pb.GetUserByIdRes)) {
    throw new Error('Expected argument of type userService.GetUserByIdRes');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_userService_GetUserByIdRes(buffer_arg) {
  return user_pb.GetUserByIdRes.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_userService_GetUserInfoByIdsReq(arg) {
  if (!(arg instanceof user_pb.GetUserInfoByIdsReq)) {
    throw new Error('Expected argument of type userService.GetUserInfoByIdsReq');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_userService_GetUserInfoByIdsReq(buffer_arg) {
  return user_pb.GetUserInfoByIdsReq.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_userService_GetUserInfoByIdsRes(arg) {
  if (!(arg instanceof user_pb.GetUserInfoByIdsRes)) {
    throw new Error('Expected argument of type userService.GetUserInfoByIdsRes');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_userService_GetUserInfoByIdsRes(buffer_arg) {
  return user_pb.GetUserInfoByIdsRes.deserializeBinary(new Uint8Array(buffer_arg));
}


var UserServiceService = exports.UserServiceService = {
  getUserById: {
    path: '/userService.UserService/GetUserById',
    requestStream: false,
    responseStream: false,
    requestType: user_pb.GetUserByIdReq,
    responseType: user_pb.GetUserByIdRes,
    requestSerialize: serialize_userService_GetUserByIdReq,
    requestDeserialize: deserialize_userService_GetUserByIdReq,
    responseSerialize: serialize_userService_GetUserByIdRes,
    responseDeserialize: deserialize_userService_GetUserByIdRes,
  },
  getUserInfoByIds: {
    path: '/userService.UserService/GetUserInfoByIds',
    requestStream: false,
    responseStream: false,
    requestType: user_pb.GetUserInfoByIdsReq,
    responseType: user_pb.GetUserInfoByIdsRes,
    requestSerialize: serialize_userService_GetUserInfoByIdsReq,
    requestDeserialize: deserialize_userService_GetUserInfoByIdsReq,
    responseSerialize: serialize_userService_GetUserInfoByIdsRes,
    responseDeserialize: deserialize_userService_GetUserInfoByIdsRes,
  },
};

exports.UserServiceClient = grpc.makeGenericClientConstructor(UserServiceService);
