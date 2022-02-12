//
//  SelectRestaurantView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/23.
//

import UIKit


class RestaurantCollectionView: UIViewController{
    @IBOutlet weak var collectionView: UICollectionView!

    override func viewDidLoad() {
        super.viewDidLoad()
        restaurantsInit()
    }
}

extension RestaurantCollectionView: UICollectionViewDataSource{
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return CafeteriaManager.shared.cafeterias.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "RestaurantCell", for: indexPath) as? RestaurantCollectionViewCell else{
            return UICollectionViewCell()
        }
        let item = CafeteriaManager.shared.cafeterias[indexPath.item]
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
        guard let restaurantInfoVC = self.storyboard?.instantiateViewController(withIdentifier: "RestaurantInfoView") as? RestaurantInfoView else {return}
        restaurantInfoVC.cafeteriaId = CafeteriaManager.shared.getCafeteriaId(index: indexPath.item)
        restaurantInfoVC.cafeteria = CafeteriaManager.shared.cafeterias[indexPath.item]
        self.navigationController?.pushViewController(restaurantInfoVC, animated: true)
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

extension RestaurantCollectionView{
    func restaurantsInit(){
        let model =  RestaurantInfoModel(token: UserDefaults.standard.string(forKey: "loginToken")!)
        CafeteriaManager.shared.getRestaurants(model: model) { responseModel in
            for data in responseModel{
                let cafeteriaModel = CafeteriaManager.shared.convertRestaurant(response: data)
                CafeteriaManager.shared.cafeterias.append(cafeteriaModel)
            }
            self.collectionView.reloadData()
        }
    }
}
