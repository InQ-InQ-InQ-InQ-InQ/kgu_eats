//
//  OrderMenuModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/17.
//

import Foundation
import Alamofire
struct OrderMenuModel{
    var headers: HTTPHeaders = ["Accept" : "*/*", "Content-Type" : "application/json"]
    var parameters: Parameters = [:]
    init(token: String, cart: Cart){
        self.headers["X-AUTH-TOKEN"] = token
        self.parameters["storeId"] = cart.currentRestaurantId
        self.parameters["menuId"] = cart.cart.first?.menu.id
    }
    
}



//{
//    "storeId":2,
//    "menuId":8
//}
