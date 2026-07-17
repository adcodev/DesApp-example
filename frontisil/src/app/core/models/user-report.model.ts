export interface CountByDomain {
  domain: string;
  count: number;
}

export interface CountByRole {
  role: string;
  count: number;
}

export interface ReportUser {
  id: string;
  name: string;
  email: string;
  role: string;
}

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

export interface UserReportResponse {
  countByDomain: CountByDomain[];
  countByRole: CountByRole[];
  users: PageResponse<ReportUser>;
}

export interface UserReportParams {
  emailType?: string;
  role?: string;
  page?: number;
  size?: number;
}
