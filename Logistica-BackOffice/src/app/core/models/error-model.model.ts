export interface ErrorModelModel {
  error: {
    code: number;
    message: string;
    details: string;
  };
  result: any;
  success: boolean;
  targetUrl: string;
  unAuthorizedRequest: boolean;
}


