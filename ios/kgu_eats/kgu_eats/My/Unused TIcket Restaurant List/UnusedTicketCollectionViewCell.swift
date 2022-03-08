//
//  UnusedTicketCollectionViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/17.
//

import UIKit

class UnusedTicketCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var restaurantName: UILabel!
    @IBOutlet weak var UnusedCount: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        self.UnusedCount.layer.masksToBounds = true
        self.UnusedCount.layer.cornerRadius = 4
        self.image.layer.cornerRadius = 4
    }
    
    func updateUI(item: Cafeteria, count: Int){
        self.image.image = item.restaurantImage?.first
        self.restaurantName.text = item.name
        self.UnusedCount.text = String(count)
        
        
    }
}
