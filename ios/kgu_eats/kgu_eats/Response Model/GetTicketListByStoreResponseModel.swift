//
//  GetTicketListResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/17.
//

import Foundation
struct GetTicketListByStoreResponseModel: Decodable{
    let amount: Int
    let id: Int
    var menuList: MenuDto
    
    enum CodingKeys: String, CodingKey{
        case amount
        case id
        case menuList = "menuDto"
    }
    
}
struct MenuDto: Decodable{
    let price: Int
    let id: Int
    let name: String
    
    enum CodingKeys: String, CodingKey{
        case price
        case id
        case name
    }
}
//[
//    {
//        "amount": 4,
//        "id": 1,
//        "menuDto": {
//            "price": 10000,
//            "id": 8,
//            "name": "낙곱새"
//        }
//    },
//    {
//        "amount": 8,
//        "id": 2,
//        "menuDto": {
//            "price": 8000,
//            "id": 9,
//            "name": "육개장"
//        }
//    }
//]
