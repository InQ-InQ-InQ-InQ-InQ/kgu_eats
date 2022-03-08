//
//  OrderMenuResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/17.
//

import Foundation
struct OrderMenuResponseModel: Decodable{
    let id: Int
    let menuId: Int
    let date: String
    
    enum CodingKeys: String, CodingKey{
        case id
        case menuId
        case date = "orderDate"
    }
}

//    {
//      "id": 0,
//      "menuId": 0,
//      "orderDate": "yyyy-MM-dd HH:mm:ss"
//    }
