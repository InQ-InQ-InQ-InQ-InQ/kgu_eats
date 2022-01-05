//
//  RestaurantInfoView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/24.
//

import UIKit

class RestaurantInfoView: UIViewController{
    
    
    var cafeteria: Cafeteria?
    
    func getItem(with: Cafeteria?) {
        cafeteria = with
    }
    func delegateData(data: Int) {
        self.cafeteria = CafeteriaManager().getCafeteria(index: data)
    }
    
//    var currentPage: Int = 0
    
    @IBOutlet weak var thumbnailView: UICollectionView!
    @IBOutlet weak var categoryView: UICollectionView!
    @IBOutlet weak var infoView: UICollectionView!
    @IBOutlet weak var name: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        //thumbnailView.decelerationRate = .fast // 무슨 뜻인교?
        thumbnailView.delegate = self
        thumbnailView.dataSource = self
        
        name.text = cafeteria?.name
        
        categoryView.delegate = self
        categoryView.dataSource = self
        
//        infoView.delegate = self
//        infoView.dataSource = self
    }
}
extension RestaurantInfoView: UICollectionViewDelegate{
    // TODO: Tumbnail 클릭하면 전체 크기 이미지 슬라이더
    // TODO: categoryBar 클릭하면 뷰 움직이는 것
    
}
extension RestaurantInfoView: UICollectionViewDataSource{
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if collectionView == thumbnailView{
            return cafeteria?.restaurantImage?.count ?? 0
        }else{
            return 3
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if collectionView == thumbnailView{
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ThumnailImage", for: indexPath) as? ThumnailCollectionViewCell else {
                return UICollectionViewCell()
            }
            guard let image = cafeteria?.restaurantImage![indexPath.row] else {
                return cell
            }
            cell.updateImage(item: image)
            return cell
        }else{
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "categoryBar", for: indexPath) as? CategoryViewCell else{
                return UICollectionViewCell()
            }
            cell.setTitle(index: indexPath.row)
            return cell
        }
    }
}
extension RestaurantInfoView: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if collectionView == thumbnailView{
            let height = collectionView.bounds.height
            let width = collectionView.bounds.width
            return CGSize(width: width, height: height)
        }else{
            let width = collectionView.bounds.width / 3
            let height: CGFloat = 40
            return CGSize(width: width, height: height)
        }
    }
    // section 내부 셀 간의 spacing 0
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    //section 사이 공간 0
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
}

// TODO: 메뉴판 구성
