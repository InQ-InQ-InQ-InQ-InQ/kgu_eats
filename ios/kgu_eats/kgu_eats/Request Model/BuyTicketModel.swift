//
//  BuyTicketModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/15.
//

import Foundation
import Alamofire
struct BuyTicketModel{
    var headers: HTTPHeaders = ["Accept" : "*/*", "Content-Type" : "application/json"]
    
    var parameters: Parameters = [:]
    
    init(token: String, cart: Cart){
        self.headers["X-AUTH-TOKEN"] = token
        self.parameters["storeId"] = cart.currentRestaurantId
        var cartList: [Parameters] = []
        for i in cart.cart{
            cartList.append(OrderUnits(menuId: i.menu.id, amount: i.amount).parameters)
        }
        self.parameters["orderUnits"] = cartList       
    }
}
struct OrderUnits{
    var parameters: Parameters = [:]
    init(menuId: Int, amount: Int){
        self.parameters["menuId"] = menuId
        self.parameters["amount"] = amount
    }
}

//    "{
//        "storeId":2,
//        "orderUnits":[
//            {
//                "menuId":8,
//                "amount":1
//            },
//            {
//                "menuId":9,
//                "amount":2
//            }
//        ]
//    }"
