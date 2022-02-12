//
//  MenuModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/12.
//

import Foundation
import Alamofire

struct MenuModel{
    var headers: HTTPHeaders = ["Accept" : "*/*"]
    var restaurantId: Int
    init(token: String, restaurantId: Int){
        self.headers["X-AUTH-TOKEN"] = token
        self.restaurantId = restaurantId
    }
}
