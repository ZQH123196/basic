// package: userService
// file: user.proto

/* tslint:disable */
/* eslint-disable */

import * as grpc from "@grpc/grpc-js";
import * as user_pb from "./user_pb";

interface IUserServiceService extends grpc.ServiceDefinition<grpc.UntypedServiceImplementation> {
    getUserById: IUserServiceService_IGetUserById;
    getUserInfoByIds: IUserServiceService_IGetUserInfoByIds;
}

interface IUserServiceService_IGetUserById extends grpc.MethodDefinition<user_pb.GetUserByIdReq, user_pb.GetUserByIdRes> {
    path: "/userService.UserService/GetUserById";
    requestStream: false;
    responseStream: false;
    requestSerialize: grpc.serialize<user_pb.GetUserByIdReq>;
    requestDeserialize: grpc.deserialize<user_pb.GetUserByIdReq>;
    responseSerialize: grpc.serialize<user_pb.GetUserByIdRes>;
    responseDeserialize: grpc.deserialize<user_pb.GetUserByIdRes>;
}
interface IUserServiceService_IGetUserInfoByIds extends grpc.MethodDefinition<user_pb.GetUserInfoByIdsReq, user_pb.GetUserInfoByIdsRes> {
    path: "/userService.UserService/GetUserInfoByIds";
    requestStream: false;
    responseStream: false;
    requestSerialize: grpc.serialize<user_pb.GetUserInfoByIdsReq>;
    requestDeserialize: grpc.deserialize<user_pb.GetUserInfoByIdsReq>;
    responseSerialize: grpc.serialize<user_pb.GetUserInfoByIdsRes>;
    responseDeserialize: grpc.deserialize<user_pb.GetUserInfoByIdsRes>;
}

export const UserServiceService: IUserServiceService;

export interface IUserServiceServer {
    getUserById: grpc.handleUnaryCall<user_pb.GetUserByIdReq, user_pb.GetUserByIdRes>;
    getUserInfoByIds: grpc.handleUnaryCall<user_pb.GetUserInfoByIdsReq, user_pb.GetUserInfoByIdsRes>;
}

export interface IUserServiceClient {
    getUserById(request: user_pb.GetUserByIdReq, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    getUserById(request: user_pb.GetUserByIdReq, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    getUserById(request: user_pb.GetUserByIdReq, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
    getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
    getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
}

export class UserServiceClient extends grpc.Client implements IUserServiceClient {
    constructor(address: string, credentials: grpc.ChannelCredentials, options?: object);
    public getUserById(request: user_pb.GetUserByIdReq, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    public getUserById(request: user_pb.GetUserByIdReq, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    public getUserById(request: user_pb.GetUserByIdReq, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserByIdRes) => void): grpc.ClientUnaryCall;
    public getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
    public getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, metadata: grpc.Metadata, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
    public getUserInfoByIds(request: user_pb.GetUserInfoByIdsReq, metadata: grpc.Metadata, options: Partial<grpc.CallOptions>, callback: (error: grpc.ServiceError | null, response: user_pb.GetUserInfoByIdsRes) => void): grpc.ClientUnaryCall;
}
