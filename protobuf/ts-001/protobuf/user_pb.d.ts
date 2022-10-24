// package: userService
// file: user.proto

/* tslint:disable */
/* eslint-disable */

import * as jspb from "google-protobuf";

export class User extends jspb.Message { 
    getId(): number;
    setId(value: number): User;
    getName(): string;
    setName(value: string): User;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): User.AsObject;
    static toObject(includeInstance: boolean, msg: User): User.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: User, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): User;
    static deserializeBinaryFromReader(message: User, reader: jspb.BinaryReader): User;
}

export namespace User {
    export type AsObject = {
        id: number,
        name: string,
    }
}

export class Users extends jspb.Message { 
    clearUsersList(): void;
    getUsersList(): Array<User>;
    setUsersList(value: Array<User>): Users;
    addUsers(value?: User, index?: number): User;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): Users.AsObject;
    static toObject(includeInstance: boolean, msg: Users): Users.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: Users, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): Users;
    static deserializeBinaryFromReader(message: Users, reader: jspb.BinaryReader): Users;
}

export namespace Users {
    export type AsObject = {
        usersList: Array<User.AsObject>,
    }
}

export class GetUserByIdReq extends jspb.Message { 
    getId(): number;
    setId(value: number): GetUserByIdReq;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): GetUserByIdReq.AsObject;
    static toObject(includeInstance: boolean, msg: GetUserByIdReq): GetUserByIdReq.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: GetUserByIdReq, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): GetUserByIdReq;
    static deserializeBinaryFromReader(message: GetUserByIdReq, reader: jspb.BinaryReader): GetUserByIdReq;
}

export namespace GetUserByIdReq {
    export type AsObject = {
        id: number,
    }
}

export class GetUserByIdRes extends jspb.Message { 

    hasUserinfo(): boolean;
    clearUserinfo(): void;
    getUserinfo(): User | undefined;
    setUserinfo(value?: User): GetUserByIdRes;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): GetUserByIdRes.AsObject;
    static toObject(includeInstance: boolean, msg: GetUserByIdRes): GetUserByIdRes.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: GetUserByIdRes, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): GetUserByIdRes;
    static deserializeBinaryFromReader(message: GetUserByIdRes, reader: jspb.BinaryReader): GetUserByIdRes;
}

export namespace GetUserByIdRes {
    export type AsObject = {
        userinfo?: User.AsObject,
    }
}

export class GetUserInfoByIdsReq extends jspb.Message { 
    clearIdsList(): void;
    getIdsList(): Array<number>;
    setIdsList(value: Array<number>): GetUserInfoByIdsReq;
    addIds(value: number, index?: number): number;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): GetUserInfoByIdsReq.AsObject;
    static toObject(includeInstance: boolean, msg: GetUserInfoByIdsReq): GetUserInfoByIdsReq.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: GetUserInfoByIdsReq, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): GetUserInfoByIdsReq;
    static deserializeBinaryFromReader(message: GetUserInfoByIdsReq, reader: jspb.BinaryReader): GetUserInfoByIdsReq;
}

export namespace GetUserInfoByIdsReq {
    export type AsObject = {
        idsList: Array<number>,
    }
}

export class GetUserInfoByIdsRes extends jspb.Message { 
    clearUsersinfoList(): void;
    getUsersinfoList(): Array<User>;
    setUsersinfoList(value: Array<User>): GetUserInfoByIdsRes;
    addUsersinfo(value?: User, index?: number): User;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): GetUserInfoByIdsRes.AsObject;
    static toObject(includeInstance: boolean, msg: GetUserInfoByIdsRes): GetUserInfoByIdsRes.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: GetUserInfoByIdsRes, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): GetUserInfoByIdsRes;
    static deserializeBinaryFromReader(message: GetUserInfoByIdsRes, reader: jspb.BinaryReader): GetUserInfoByIdsRes;
}

export namespace GetUserInfoByIdsRes {
    export type AsObject = {
        usersinfoList: Array<User.AsObject>,
    }
}
