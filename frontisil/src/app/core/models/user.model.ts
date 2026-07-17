export interface UserRequest {
  name?: string;
  email?: string;
  role?: string;
}

export interface UserResponse {
  id: string;
  name: string;
  email: string;
  role: string;
}
