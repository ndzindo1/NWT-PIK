import { Component, OnInit } from '@angular/core';
import { InfoService } from '../common/info.service';
import { Router } from '@angular/router';
import { UserService } from '../common/user.service';
import { Chat } from '../common/chat';
import { ContactService } from '../common/contact.service';
import { ItemService } from '../common/item.service';

@Component({
  selector: 'app-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemInfoComponent implements OnInit {

  item: any;
  image: any;
  me: any;

  constructor( private infoService: InfoService, private router: Router, private userService: UserService, private contactService: ContactService, private itemService: ItemService ) { }

  ngOnInit() {
    this.item = this.infoService.getItem();
    this.image = this.infoService.getImage();
    this.me = this.userService.getUser();
    this.contactService.newChatSuccess.subscribe((res) => {
      this.router.navigate(['/chat']);
    });
  }

  goBack() {
    this.router.navigate(["/browse"]);
  }

  hasImage(item) {
    if (item && item.images && item.images[0]) return true;
    else return false;
  }

  getUrl() {
    return this.image;
  }

  myItem() {
    return (this.me.id == this.item.user.id);
  }

  onSold() {
    let status = { "status": true };
    this.itemService.sellItem(status, this.item.id);
    this.itemService.itemSellSuccess.subscribe((res) => {
      this.router.navigate(['/browse']);
    });
  }

  onDelete() {
    this.itemService.deleteItem(this.item.id);
    this.itemService.itemDeleteSuccess.subscribe((res) => {
      this.router.navigate(['/browse']);
    });
  }

  onContact() {
    this.contactService.createChat(this.me.id, this.item.user.id);
  }

  sold() {
    return this.item.status;
  }
}
