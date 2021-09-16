export class RoleResponseDto {
    id: number | undefined;
    name: string | undefined;
    description: string | undefined;
    privileges: PrivilegeResponseDto[] | undefined;
}

export interface RoleRequestDto {
    id?: number;
    name: string;
    description: string;
    privilegeIds: number[];
}

export interface PrivilegeResponseDto {
    id: number;
    name: string;
    description: string;
}
