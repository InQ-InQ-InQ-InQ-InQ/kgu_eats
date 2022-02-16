//
//  CartCollectionViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/14.
//

import UIKit

class CartCollectionViewCell: UICollectionViewCell {
    
    var ticket: TempTicket?
    var parentView: CartViewController?
    
    @IBOutlet weak var menuName: UILabel!
    @IBOutlet weak var menuImage: UIImageView!
    @IBOutlet weak var totalCost: UILabel!
    @IBOutlet weak var count: UILabel!
    
    @IBAction func minusButton(_ sender: Any) {
        guard let ticket = ticket else {
            return
        }

        guard var num = Int(self.count.text!) else{
            return
        }
        
        if num > 1{
            num -= 1
            self.count.text = String(num)
            self.totalCost.text = String(num * ticket.menu.price)+"원"
            Cart.shared.changeAmount(id: ticket.menu.id, amount: num)
            parentView?.updateUI()
        }else{
            // 10개를 초과할 수 없습니다. 팝업
        }
    }
    @IBAction func plusButton(_ sender: Any) {
        guard let ticket = ticket else {
            return
        }

        guard var num = Int(self.count.text!) else{
            return
        }
        
        if num < 10{
            num += 1
            self.count.text = String(num)
            self.totalCost.text = String(num * ticket.menu.price)+"원"
            Cart.shared.changeAmount(id: ticket.menu.id, amount: num)
            parentView?.updateUI()
        }else{
            // 10개를 초과할 수 없습니다. 팝업
        }
    }
    func setUI(item: TempTicket){
        self.menuName.text = item.menu.name
        self.menuImage.image = item.menu.image
        self.totalCost.text = String(item.menu.price * item.amount)+"원"
        self.count.text = String(item.amount)
    }
    
}
