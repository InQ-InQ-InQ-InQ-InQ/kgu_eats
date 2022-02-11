//
//  RestaurantResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/08.
//

import Foundation

struct RestaurantResponseModel:Decodable{
    let campusId: Int
    let name: String
    let id: Int
   
    enum CodingKeys: String, CodingKey{
        case campusId
        case name
        case id
    }
}

//[
//    {
//        "campusId": 1,
//        "name": "감성코어",
//        "id": 1
//    },
//    {
//        "campusId": 1,
//        "name": "경기드림타워",
//        "id": 2
//    }
//]
