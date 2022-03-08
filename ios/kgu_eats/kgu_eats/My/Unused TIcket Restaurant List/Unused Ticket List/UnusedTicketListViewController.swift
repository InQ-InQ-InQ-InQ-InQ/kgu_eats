//
//  UnusedTicketListViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/28.
//

import UIKit

class UnusedTicketListViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    var delegateName = ""
    var cafeteriaId: Int?
    
    @IBOutlet weak var name: UILabel!
    
    var tickets = [Ticket]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTicketList()
        self.name.text = self.delegateName
        // Do any additional setup after loading the view.
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
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

extension UnusedTicketListViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.tickets.count
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "UnusedTicketList", for: indexPath) as? UnusedTicketListViewCell else{
            return UICollectionViewCell()
        }
        guard let cafeteriaId = self.cafeteriaId else{
            return UICollectionViewCell()
        }
        let item = self.tickets[indexPath.item]
        let imageId = item.ticketInfo.id
        let image = CafeteriaManager.shared.cafeterias[cafeteriaId].menu
        CafeteriaManager.shared.getMenuImage(cafeteriaId: cafeteriaId)
        
        cell.updateUI(image: UIImage(), menuName: item.ticketInfo.name, amount: item.amount)
        return cell
    }
}

extension UnusedTicketListViewController: UICollectionViewDelegate{
    
}

extension UnusedTicketListViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = self.collectionView.bounds.width - 20 * 2
        let height: CGFloat = 128
        return CGSize(width: width, height: height)
    }
}
extension UnusedTicketListViewController{
    func setTicketList(){
        let model = GetTicketListByStoreModel(token: UserDefaults.standard.string(forKey: "loginToken")!, id: self.cafeteriaId!)
        TicketManager.shared.getTicketListByStore(model: model) { responseModel in
            for i in responseModel{
                let ticketInfo = TicketMenuDto(price: i.menuList.price, id: i.menuList.id, name: i.menuList.name)
                let ticket = Ticket(id: i.id, amount: i.amount, ticketInfo: ticketInfo)
                self.tickets.append(ticket)
            }
            
            self.collectionView.reloadData()
        }
        
    }
}
