//
//  Cart.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/14.
//

import Foundation
import Alamofire
class Cart{
    static let shared = Cart()
    
    let buyTicketRequestUrl = "\(NetWork.baseURL)/order/ticket"
    
    
    var currentRestaurantId = 0
    var cart: [TempTicket] = []
    //var menuCart = [Menu]()
    
    func getTotalCost()->Int{
        var total = 0
        for i in cart{
            total += i.amount * i.menu.price
        }
        return total
    }
    
    func changeAmount(id: Int, amount: Int){
        for i in cart{
            if i.menu.id == id{
                i.changeAmount(amount: amount)
            }
        }
    }
    
    func successBuyTicket(){
        self.cart.removeAll()
    }
    
}
extension Cart{
    func buyTicketRequest(model: BuyTicketModel){
        AF.request(buyTicketRequestUrl, method: .post, parameters: model.parameters, encoding: JSONEncoding.default, headers: model.headers).response { response in
            
            guard let statusCode = response.response?.statusCode else{
                print("get statusCode error")
                return
            }
            
            switch statusCode{
            case 200..<300:
                do{
                    let decodeData = try JSONDecoder().decode(TicketResponseModel.self, from: response.data!)
                    //TicketResponseModel을 활용해 response 받기 -> 어따가 받을겨
                    let ticket = Cart.shared.convertTicket(model: decodeData)
                    TicketManager.shared.tickets.append(ticket)
                    Cart.shared.cart.removeAll()
                }catch{
                    print("JSONDecoder error")
                }
            default:
                print("buyTicketRequest default")
            }
        }
    }
    func convertTicket(model: TicketResponseModel) -> Ticket{
        let id = model.id
        let date = model.date
        let ticketList = model.ticketList
        return Ticket(id: id, date: date, ticketList: ticketList)
    }
}
class TempTicket{
    var amount: Int
    let menu: Menu
    
    init(amount: Int, menu: Menu){
        self.amount = amount
        self.menu = menu
    }
    func changeAmount(amount: Int){
        self.amount = amount
    }
}
