//
//  restaurantInfoModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/08.
//

import Foundation
import Alamofire

struct RestaurantInfoModel{
    var headers: HTTPHeaders = ["accept":"*/*"]
    init(token: String){
        self.headers["X-AUTH-TOKEN"] = token
    }
    
}

// [Input]
// X-AUTH-TOKEN
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDE2MTAwMTAiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjQ0MzAxODQ4LCJleHAiOjE2NDQzMDU0NDh9.kPNlo1YTQPgwMHDW4PFYq1bDvc3l6cdx3UXF9WVgZAg




// [Output]
//[
//  {
//    "campusId": 0,
//    "id": 0,
//    "name": "string"
//  }
//]
