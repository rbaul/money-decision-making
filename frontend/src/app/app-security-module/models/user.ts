import { RoleResponseDto } from './role';

export class User {
    username: string;
    password: string;

    constructor(username: string, password: string) {
        this.username = username;
        this.password = password;
    }
}

export class UserResponseDto {
    id: number | undefined;
    username: string | undefined;
    firstName: string | undefined;
    lastName: string | undefined;
    roles: RoleResponseDto[] = [];
}

export interface UserRequestDto {
    // id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    roleIds: number[];
}
