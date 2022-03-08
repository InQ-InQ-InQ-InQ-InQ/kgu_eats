//
//  TicketResponseModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/15.
//

import Foundation
struct OrderTicketResponseModel: Decodable{
    let id: Int
    let date: String
    var ticketList: [TicketUnit]
    
    enum CodingKeys: String, CodingKey{
        case id
        case date = "orderDate"
        case ticketList = "orderTicketHistoryUnitDtos"
    }
}
struct TicketUnit: Codable{
    let ticketId: Int
    let amount: Int
    
    enum CodingKeys: String, CodingKey{
        case ticketId
        case amount
    }
}


//{
//    "id": 1,
//    "orderDate": "2022-02-15 08:37:02",
//    "orderTicketHistoryUnitDtos": [
//        {
//            "ticketId": 1,
//            "amount": 1
//        },
//        {
//            "ticketId": 2,
//            "amount": 2
//        }
//    ]
//}


