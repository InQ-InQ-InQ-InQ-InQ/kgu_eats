//
//  RestaurantInfoView.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/24.
//

import UIKit

class RestaurantInfoView: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
}

class CategoryTab: UICollectionViewCell{
    @IBOutlet weak var tabTitle: UILabel!
    let titleList: [String] = ["정보", "메뉴", "리뷰"]
    
    func setTitle(_ index: Int){
        self.tabTitle.text = titleList[index]
    }
    
    override var isSelected: Bool{
        willSet{
            if newValue{
                tabTitle.textColor = .black
            }else{
                tabTitle.textColor = .lightGray
            }
        }
    }
}

//extension CategoryTab: UICollectionViewDataSource{
//    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
//        return UICollectionViewCell()
//    }
//    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        return 3
//    }
//}



