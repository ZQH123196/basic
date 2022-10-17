import grpc from "@grpc/grpc-js"
import { IUser } from '../protobuf/user_grpc_pb';

const server = new grpc.Server()


server.addService<IUer>

