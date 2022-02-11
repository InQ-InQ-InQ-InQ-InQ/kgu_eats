//
//  LoginResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/07.
//

import Foundation
struct LoginResponseModel{
    let accessToken: String
    init(accessToken: String){
        self.accessToken = accessToken
    }
}

// X-AUTH-TOKEN
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDE2MTAwMTAiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjQ0MzAxODQ4LCJleHAiOjE2NDQzMDU0NDh9.kPNlo1YTQPgwMHDW4PFYq1bDvc3l6cdx3UXF9WVgZAg
