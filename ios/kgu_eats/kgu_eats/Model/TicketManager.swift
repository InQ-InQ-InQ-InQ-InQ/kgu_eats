//
//  TicketManager.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/15.
//

import Foundation
class TicketManager{
    static let shared = TicketManager()
    var tickets = [Ticket]()
    
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
