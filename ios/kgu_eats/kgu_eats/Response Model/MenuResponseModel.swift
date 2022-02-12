//
//  MenuResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/12.
//

import Foundation

struct MenuResponseModel: Decodable{
    let price: Int
    let id: Int
    let name: String
    
    enum CodingKeys: String, CodingKey{
        case price
        case id
        case name
    }
}


//{
//    "price": 8000,
//    "id": 1,
//    "name": "돈까스"
//},
//{
//    "price": 6000,
//    "id": 2,
//    "name": "카레라이스"
//},
//{
//    "price": 6000,
//    "id": 3,
//    "name": "오므라이스"
//},
//{
//    "price": 10000,
//    "id": 4,
//    "name": "육회비빔밥"
//}
//]
