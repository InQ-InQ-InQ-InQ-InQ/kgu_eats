//
//  SelectRestaurantView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/23.
//

import UIKit
//
//protocol ReturnDelegate{
//    func delegateData(data: Int)
//}

class RestaurantCollectionView: UIViewController{
    @IBOutlet weak var collectionView: UICollectionView!
    
    let cafeteriaManager = CafeteriaManager()

    override func viewDidLoad() {
        super.viewDidLoad()
    }
}


extension RestaurantCollectionView: UICollectionViewDataSource{
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return cafeteriaManager.cafeterias.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "RestaurantCell", for: indexPath) as? RestaurantCollectionViewCell else{
            return UICollectionViewCell()
        }
        let item = cafeteriaManager.getCafeteria(index: indexPath.item)
        cell.updateUI(item)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        switch kind{
        case UICollectionView.elementKindSectionHeader:
            guard let header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "RestaurantHeader", for: indexPath) as? RestaurantCollectionHeaderView else{
                return UICollectionReusableView()
            }
            // 헤더 이미지 바꾸기
            // 식당 정보 구조체에서 가져와서 아래 메소드에 넣기
            
            header.updateUI(UIImage(named: "헤더 이미지")!)

            return header
        default:
            return UICollectionReusableView()
        }
    }
}
extension RestaurantCollectionView: UICollectionViewDelegate{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let restaurantVC = self.storyboard?.instantiateViewController(withIdentifier: "RestaurantInfoView") as? RestaurantInfoView else {return}
        restaurantVC.cafeteria = cafeteriaManager.getCafeteria(index: indexPath.item)
        
//        guard let menuContainer = self.storyboard?.instantiateViewController(withIdentifier: "MenuContainer") as? MenuViewController else {return}
//        menuContainer.cafeteria = cafeteriaManager.getCafeteria(index: indexPath.item)
        // TODO: 리뷰 컨테이너, 인포 컨테이너에도 딜리게이트 전달
        
        self.navigationController?.pushViewController(restaurantVC, animated: true)
    }
}

extension RestaurantCollectionView: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let itemSpacing: CGFloat = 20
        let margin: CGFloat = 20
        let width: CGFloat = (collectionView.bounds.width-itemSpacing-margin*2) / 2
        let height: CGFloat = width + 40
        
        return CGSize(width: width, height: height)
    }
}

