//
//  ThumnailCollectionView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/29.
//

import Foundation
import UIKit

class ThumnailCollectionViewCell: UICollectionViewCell{
    
    @IBOutlet weak var thumbnailImage: UIImageView!
    
    
    func updateImage(item: UIImage){
        thumbnailImage.image = item
    }
    
    
    
    
    
    
}
