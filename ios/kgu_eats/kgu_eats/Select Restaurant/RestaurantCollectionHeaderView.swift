//
//  RestaurantCollectionHeaderView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/27.
//

import UIKit

class RestaurantCollectionHeaderView: UICollectionReusableView {

    @IBOutlet weak var thumbnail: UIImageView!
    

    func updateUI(_ image: UIImage){
        self.thumbnail.image = image
    }
}
