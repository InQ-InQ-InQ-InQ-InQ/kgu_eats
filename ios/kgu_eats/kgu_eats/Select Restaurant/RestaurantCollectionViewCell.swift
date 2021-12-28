//
//  RestaurantViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/27.
//

import UIKit

class RestaurantCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var restaurantImage: UIImageView!
    @IBOutlet weak var restaurantName: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        restaurantImage.layer.cornerRadius = 4
    }
    
    func updateUI(_ item: Cafeteria?){
        guard let data = item else{
            return
        }
        self.restaurantImage.image = data.thumnailImage
        self.restaurantName.text = data.name
    }
    
}
