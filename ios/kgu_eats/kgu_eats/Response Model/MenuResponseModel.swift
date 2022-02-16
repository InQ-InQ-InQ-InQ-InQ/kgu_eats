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
//[
//    {
//        "price": 8000,
//        "id": 5,
//        "name": "황금돈까스"
//    },
//    {
//        "price": 6000,
//        "id": 6,
//        "name": "치즈돈까스"
//    },
//    {
//        "price": 6000,
//        "id": 7,
//        "name": "제육덮밥"
//    },
//    {
//        "price": 10000,
//        "id": 8,
//        "name": "낙곱새"
//    },
//    {
//        "price": 8000,
//        "id": 9,
//        "name": "육개장"
//    },
//    {
//        "price": 6000,
//        "id": 10,
//        "name": "비빔냉면"
//    },
//    {
//        "price": 6000,
//        "id": 11,
//        "name": "물냉면"
//    },
//    {
//        "price": 10000,
//        "id": 12,
//        "name": "스팸마요덮밥"
//    }
//]
