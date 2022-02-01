//
//  Review.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/12.
//

import Foundation
import UIKit
struct Review{
    var name: String // 가게명
    //var grade: Int // 평점
    var content: String // 리뷰 내용
    var images: [UIImage] // 음식 사진
    init(name: String, content: String, images: [UIImage]){
        self.name = name
        self.content = content
        self.images = images
    }
}
