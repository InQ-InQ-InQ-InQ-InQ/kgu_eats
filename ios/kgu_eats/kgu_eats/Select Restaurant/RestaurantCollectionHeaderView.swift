//
//  RestaurantCollectionHeaderView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/27.
//

import UIKit

class RestaurantCollectionHeaderView: UICollectionReusableView {

    @IBOutlet weak var thumbnail: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        
        thumbnail.layer.cornerRadius = 4
    }

    func updateUI(_ image: UIImage){
        self.thumbnail.image = image
    }
}
