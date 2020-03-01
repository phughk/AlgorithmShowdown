use std::io;
fn main() {
    let mut num_items_str = String::new();
    let mut num_arr_str = String::new();

    io::stdin().read_line(&mut num_items_str).ok().expect("read error");
    io::stdin().read_line(&mut num_arr_str).ok().expect("read error");

    let mut num_items : i32 = num_items_str.trim().parse().ok().expect("parse error");

}
