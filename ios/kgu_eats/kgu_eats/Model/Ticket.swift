//
//  Ticket.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/04.
//

import Foundation

class Ticket{
    let id: Int
    var amount: Int
    var ticketInfo: TicketMenuDto
    init(id: Int, amount: Int, ticketInfo: TicketMenuDto){
        self.id = id
        self.amount = amount
        self.ticketInfo = ticketInfo
    }
    
}
class TicketMenuDto{
    let price: Int
    let id: Int
    let name: String
    
    init(price: Int, id: Int, name: String){
        self.price = price
        self.id = id
        self.name = name
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