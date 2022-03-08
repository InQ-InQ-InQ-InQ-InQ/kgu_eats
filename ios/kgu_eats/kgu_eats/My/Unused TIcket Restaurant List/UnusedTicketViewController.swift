//
//  UnusedTicketViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/16.
//

import UIKit

class UnusedTicketViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
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

extension UnusedTicketViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return CafeteriaManager.shared.cafeterias.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "UnusedTicket", for: indexPath) as? UnusedTicketCollectionViewCell else{
            return UICollectionViewCell()
        }
        let item = CafeteriaManager.shared.cafeterias[indexPath.item]
        var count = 0
        let model = GetTicketListByStoreModel(token: UserDefaults.standard.string(forKey: "loginToken")!, id: item.id)
        
        TicketManager.shared.getTicketListByStore(model: model) { responseModel in
            for data in responseModel{
                count += data.amount
            }
            cell.updateUI(item: item, count: count)
        }
        
        cell.updateUI(item: item, count: count)
        //식당별 남은 식권 갯수 알려주는거
        // amount만 뽑아내는 responseModel 만들어서 해야할 듯
        return cell
    }
}
extension UnusedTicketViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = (collectionView.bounds.width - 20 - 20 * 2) / 2
        let height = width + 40
        return CGSize(width: width, height: height)
    }
}
extension UnusedTicketViewController: UICollectionViewDelegate{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let vc = self.storyboard?.instantiateViewController(withIdentifier: "ClickedUnusedTicketMenu") as? UnusedTicketListViewController else {
            print("error")
            return
        }
        vc.cafeteriaId = CafeteriaManager.shared.cafeterias[indexPath.item].id
        vc.delegateName = CafeteriaManager.shared.cafeterias[indexPath.item].name
        self.navigationController?.pushViewController(vc, animated: true)
        
    }
 
}
