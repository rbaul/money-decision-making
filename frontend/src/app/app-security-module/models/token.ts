export interface Token {
    sub: string;
    exp: number;
    iat: number;
    roles: string[];
    privileges: string[];
}
