//
//  RestaurantViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/27.
//

import UIKit

class RestaurantCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var restaurantImage: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        restaurantImage.layer.cornerRadius = 4
    }
    
    func updateUI(_ image: UIImage?){
        guard image == nil else{
            return
        }
        self.restaurantImage.image = image
    }
    
}
