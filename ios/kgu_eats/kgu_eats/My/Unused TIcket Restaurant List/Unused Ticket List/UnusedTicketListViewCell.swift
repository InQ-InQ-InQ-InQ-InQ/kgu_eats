//
//  UnusedTicketListViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/03/07.
//

import UIKit

class UnusedTicketListViewCell: UICollectionViewCell {
    
    
    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var menuName: UILabel!
    @IBOutlet weak var amount: UILabel!
    
    func updateUI(image: UIImage, menuName: String, amount: Int){
        self.image.image = image
        self.menuName.text = menuName
        self.amount.text = "\(amount)개"
    }
}
