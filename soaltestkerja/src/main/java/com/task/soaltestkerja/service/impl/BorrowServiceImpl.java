package com.task.soaltestkerja.service.impl;

import com.task.soaltestkerja.dto.BorrowDto;
import com.task.soaltestkerja.dto.BorrowResponse;
import com.task.soaltestkerja.entity.Book;
import com.task.soaltestkerja.entity.Borrow;
import com.task.soaltestkerja.entity.Member;
import com.task.soaltestkerja.entity.Penalti;
import com.task.soaltestkerja.exception.CannotProcessException;
import com.task.soaltestkerja.exception.ResourceNotFoundException;
import com.task.soaltestkerja.repository.BookRepository;
import com.task.soaltestkerja.repository.BorrowRepository;
import com.task.soaltestkerja.repository.MemberRepository;
import com.task.soaltestkerja.repository.PenaltyRepository;
import com.task.soaltestkerja.service.IBorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements IBorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PenaltyRepository penaltyRepository;
    private boolean isNotBorrowed;



    @Override
    public void createBorrow(BorrowDto borrowDto) {

        // find member if any
        Member member = memberRepository.findById(borrowDto.getMemberCode()).orElseThrow(
                () -> new ResourceNotFoundException("Member","id", borrowDto.getMemberCode())
        );

        // find book if any
        Book book = bookRepository.findById(borrowDto.getBookCode()).orElseThrow(
                () -> new ResourceNotFoundException("Book","id", borrowDto.getBookCode())
        );

        // find member with penalty or not
        Penalti penalty = penaltyRepository.findByMemberCode(borrowDto.getMemberCode()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "id", borrowDto.getMemberCode())
        );

        // find member in table borrow -> for validation if member borrow 2 book
        List<Borrow> listMemberInBorrow = borrowRepository.findByMemberCode(borrowDto.getMemberCode());


        if (listMemberInBorrow.size() < 2 ) {


            Optional<Borrow> bookInBorrow = borrowRepository.findByBookCode(borrowDto.getBookCode());

            if (bookInBorrow.isPresent() && bookInBorrow.get().getIsReturn() == 0) {

                if (bookInBorrow.get().getBook().getCode() == borrowDto.getBookCode() && bookInBorrow.get().getMember().getCode() == borrowDto.getMemberCode()) {
                    throw new CannotProcessException("Tidak dapat memproses, karena buku ini sudah anda pinjam");
                }else {
                    throw new CannotProcessException("Tidak dapat memproses, karena buku ini sudah di pinjam orang lain ");
                }
            }else {
                if (penalty.getIsPenalty() == 0) {


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");



                    Borrow borrow = new Borrow();
                    borrow.setMember(member);
                    borrow.setBook(book);
//                    borrow.setBorrowDate(LocalDate.parse(strDate,formatter));
                    borrow.setBorrowDate(borrowDto.getBorrowDate());
                    borrow.setReturnDate(borrowDto.getBorrowDate().plusDays(7));
                    borrow.setMemberReturnBookDate(null);
                    borrow.setIsReturn(0);

                    borrowRepository.save(borrow);

                    // kurangi stock buku yang di pinjam
                    book.setStock(book.getStock()-1);
                    bookRepository.save(book);
                }else {
                    throw new CannotProcessException("anda dalam masa penalty, tidak diperbolehkan meminjam buku untuk 3 hari kedepan");
                }
            }
        }else {
            throw new CannotProcessException("Tidak dapat memproses, karena anda telah pinjam lebih dari 2 buku");
        }

    }

    @Override
    public List<BorrowResponse> getAllDataBorrow() {

        List<Borrow> borrows = borrowRepository.findAll();

        List<BorrowResponse> borrowResponses = borrows.stream().map(item -> new BorrowResponse(item.getId(),item.getMember().getName(),item.getBook().getTitle(),item.getBook().getAuthor(),item.getBorrowDate(),item.getReturnDate(),item.getMemberReturnBookDate(),item.getIsReturn())).collect(Collectors.toList());
        return borrowResponses;
    }

    @Override
    public void returningBorrow(BorrowDto borrowDto) {

        // find book has borrow member
        Optional<Borrow> foundedBookInBorrow = borrowRepository.findByBookCode(borrowDto.getBookCode());
        if (foundedBookInBorrow.isPresent()) {

            System.out.println(foundedBookInBorrow.get());

            if (foundedBookInBorrow.get().getMember().getCode().equals(borrowDto.getMemberCode())  && foundedBookInBorrow.get().getBook().getCode().equals(borrowDto.getBookCode())) {

                // kembalikan buku dan update data
                foundedBookInBorrow.get().setMemberReturnBookDate(borrowDto.getMemberReturnBookDate());
                foundedBookInBorrow.get().setIsReturn(1);

                System.out.println(foundedBookInBorrow.get());
                Borrow borrowUpdate = borrowRepository.save(foundedBookInBorrow.get());


                // kembalikan stock
                Book book = bookRepository.findById(borrowDto.getBookCode()).orElseThrow(
                        () -> new ResourceNotFoundException("Book","code", borrowDto.getBookCode())
                );

                book.setStock(1);
                bookRepository.save(book);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String format = borrowDto.getMemberReturnBookDate().format(formatter);
                LocalDate dateMemberReturn = LocalDate.parse(format,formatter);

                System.out.println("ini format = " + format);

                // check apakah member mengembalikan data lebih dari jatuh tempo
                if(dateMemberReturn.isAfter(foundedBookInBorrow.get().getReturnDate())) {

                    Penalti penalti = penaltyRepository.findByMemberCode(foundedBookInBorrow.get().getMember().getCode()).orElseThrow(
                            () -> new ResourceNotFoundException("Member","code",foundedBookInBorrow.get().getMember().getCode())
                    );

                    penalti.setIsPenalty(1);
                    penalti.setPenaltyStart(borrowDto.getMemberReturnBookDate());
                    penalti.setPenaltyEnd(borrowDto.getMemberReturnBookDate().plusDays(3));

                    penaltyRepository.save(penalti);
                }

                borrowRepository.deleteById(borrowUpdate.getId());
            } else {
                throw new CannotProcessException("anda bukan yang meminjam buku ini");
            }
        }else {
            throw new CannotProcessException("buku yang anda kembalikan bukanlah buku yang anda pinjam!");
        }



    }
}
